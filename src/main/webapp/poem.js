function onPoemLoad(poem) {
    const poemTitleSpanE1 = document.getElementById("poem-title")
    const poemContentSpanE1 = document.getElementById('poe-content');

    poemTitleSpanE1.textContent = poem.title;
    poemContentSpanE1.textContent = poem.content;
}

function onPoemResponse() {
    if(this.status === OK){
        clearMessages();
        showContents(['poem-content', 'back-to-profile-content']);
        onPoemLoad(JSON.parse(this.responseText));
    }else{
        onOtherResponse(poemsContentDivEl, this);
    }
}
