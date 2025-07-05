document.addEventListener('DOMContentLoaded', () => {
    chrome.storage.local.get(['researchNotes'], function(result) {
       if (result.researchNotes) {
        document.getElementById('notes').value = result.researchNotes;
       } 
    });

    document.getElementById('summarizeBtn').addEventListener('click', summarizeText);
    document.getElementById('saveNotesBtn').addEventListener('click', saveNotes);
});

async function summarizeText() {
    try {
        // Show shimmer, hide previous results
        document.getElementById('loading-placeholder').style.display = 'block';
        document.getElementById('results').innerHTML = '';

        const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
        const [{ result }] = await chrome.scripting.executeScript({
            target: { tabId: tab.id },
            function: () => window.getSelection().toString()
        });

        if (!result) {
            document.getElementById('loading-placeholder').style.display = 'none';
            showResult('Please select some text first');
            return;
        }

        const response = await fetch('http://localhost:8080/api/research/process', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ content: result, operation: 'summarize' })
        });

        if (!response.ok) {
            throw new Error(`API Error: ${response.status}`);
        }

        const text = await response.text();

        // Hide shimmer
        document.getElementById('loading-placeholder').style.display = 'none';

        showResult(text.replace(/\n/g, '<br>'));

    } catch (error) {
        document.getElementById('loading-placeholder').style.display = 'none';
        showResult('Error: ' + error.message);
    }
}



async function saveNotes() {
    const notes = document.getElementById('notes').value;
    chrome.storage.local.set({ 'researchNotes': notes}, function() {
        alert('Notes saved successfully');
    });
}


function showResult(content) {
    document.getElementById('results').innerHTML = `<div class="result-item"><div class="result-content">${content}</div></div>`;
}