const inputFile = document.getElementById('file-input');
const applyFrom = document.getElementById('apply_form');
function openFileUploader() {
   inputFile.click()
}

inputFile.addEventListener('change', (e) => {

    const file =  e.target.files[0]
    let reader = new FileReader();
    reader.onload = (e) => {
        let imageBox = document.getElementById('image-box');
        let imageName = document.createElement('span');
        imageName.classList.add('image_name');
        imageBox.innerHTML = ''
        imageName.innerText = file.name
        imageBox.appendChild(imageName)
    }
    reader.readAsDataURL(file)
})

applyFrom.addEventListener('submit', (e) => {
    e.preventDefault();
    const cafeId = document.getElementById('save_cafe_id').value
    const url = '/api/seller_apply/' + cafeId
    let formData = new FormData();
    formData.append('file', inputFile.files[0])

    fetch(url, {
        method: 'POST',
        body: formData
    })
            .then(response => {
                if (!(response.status == 201)) {
                    console.log(response)
                    throw new Error('The request failed');
                }
                return response.json()
            })
            .then(data => {
                alert(data.message)
            })
})


