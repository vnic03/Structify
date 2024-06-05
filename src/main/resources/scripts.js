async function fetchData() {
    const response = await fetch(
        'http://localhost:8080/structures'
    );
    const data = await response.json();
    const [queueData, stackData] = data;
    visualizeQueue(queueData);
    visualizeStack(stackData);
}

function visualizeQueue(queueData) {
    const queueDiv = document.getElementById('queue');
    queueDiv.innerHTML = '';
    queueData.array.forEach(item => {
        const element = document.createElement('div');
        element.textContent = item !== null ? item : 'NULL';
        queueDiv.appendChild(element);
    });
}

function visualizeStack(stackData) {
    const stackDiv = document.getElementById('stack');
    stackDiv.innerHTML = '';
    stackData.array.forEach(item => {
        const element = document.createElement('div');
        element.textContent = item !== null ? item : 'NULL';
        stackDiv.insertBefore(element, stackDiv.firstChild);
    });
}

fetchData();

