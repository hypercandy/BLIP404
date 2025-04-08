const deletionModalConfirmButton = document.getElementById("deletionModalConfirmButton");

function setupDeletionModal(id) {
    deletionModalConfirmButton.onclick = async () => {
        const url = window.location.origin + "/renovations/" + id;
        await fetch(url, {
            method: 'delete',
        });
        location.reload();
        return false;
    }
}