const descriptionInput = document.getElementById('description');
const descriptionWordLimitText = document.getElementById('descriptionWordLimit');

const descriptionInputLengthCounterUpdater = () => {
    const graphemes = [...new Intl.Segmenter().segment(descriptionInput.value)].length;
    descriptionWordLimitText.innerText = graphemes + '/512';
};

descriptionInputLengthCounterUpdater();
descriptionInput.addEventListener('input', descriptionInputLengthCounterUpdater);