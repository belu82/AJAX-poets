let poemsTableEl;
let poemsTableBodyEl;

function onPoemClicked() {
    const poemId = this.dataset.poemId;

    const params = new URLSearchParams();
    params.append('id', poemId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poem?' + params.toString());
    xhr.send();
}

function addPoemAddResponse() {
    clearMessages();
    if(this.status === OK){
        appendPoem(JSON.parse(this.responseText));
    }else {
        onOtherResponse(poemsContentDivEl, this);
    }
}

function onPoemAddClicked() {
    const poemFormEl = document.forms['poem-form'];
    const titleInputEl = poemFormEl.querySelector('input[name="title"]');
    const title = titleInputEl.value;
    const contentInputEl = poemFormEl.querySelector('input[name="content"]');
    const content = contentInputEl.value;
    const params = new URLSearchParams();
    params.append('title', title);
    params.append('content', content);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load',addPoemAddResponse );
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/poems');
    xhr.send(params);
}

/*
function appendPoem(poem) {
    const idTdEl = document.createElement('td');
    idTdEl.textContent = shop.id;

    const aEl = document.createElement('a');
    aEl.textContent = poem.title;
    aEl.href = 'javascript:void(0);';
    aEl.dataset.poemId = poem.id;
    aEl.addEventListener('click', onPoemClicked);

    const titleTdEl = document.createELement('td');
    titleTdEl.appendChild(aEl);

    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(titleTdEl);
    poemsTableBodyEl.appendChild(trEl);
}
*/
function appendPoem(poem) {
    const idTdEl = document.createElement('td');
    idTdEl.textContent = poem.id;

    const aEl = document.createElement('a');
    aEl.textContent = poem.title;
    aEl.href = 'javascript:void(0);';
    aEl.dataset.poemId = poem.id;
    aEl.addEventListener('click', onPoemClicked);

    const titleTdEl = document.createElement('td');
    titleTdEl.appendChild(aEl);

    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(titleTdEl);
    poemsTableBodyEl.appendChild(trEl);

}


function appendPoems(poems) {
    removeAllChildren(poemsTableBodyEl);

    for(let i = 0; i<poems.length;i++){
        const poem = poems[i];
        appendPoem(poem);
    }
}
function onOtherResponse(poems) {
    poemsTableEl = document.getElementById('poems');
    poemsTableBodyEl = poemsTableEl.querySelector('tbody');
    appendPoems(poems)
}

function onPoemsLoad(poems) {
    poemsTableEl = document.getElementById('poems');
    poemsTableBodyEl = poemsTableEl.querySelector('tbody');
    appendPoems(poems);
}

function onPoemsResponse() {
    if(this.status === OK){
        showContents(['poems-content', 'back-to-profile-content']);
        onPoemsLoad(JSON.parse(this.responseText))
    }else {
        onOtherResponse(poemsContentDivEl, this);
    }
}
