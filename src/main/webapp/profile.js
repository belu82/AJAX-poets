
function onProfileLoad(poet) {
    clearMessages();
    showContents(['profile-content', 'logout-content', 'back-to-profile-content', 'poems-content']);

    const userEmailSpandEl = document.getElementById('poet-email');

    userEmailSpandEl.textContent = poet.email;
}

function onPoemsClicked(){
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemsResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poems');
    xhr.send();
}
