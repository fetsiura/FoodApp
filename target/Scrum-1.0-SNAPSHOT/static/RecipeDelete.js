document.addEventListener("DOMContentLoaded", function () {

const confirmButton = document.querySelectorAll('#confirmButton')

    confirmButton.forEach(   element => {
        element.addEventListener('click', ele=> {
            ele.preventDefault()
        let res = confirm('Czy na pewno chcesz usunąć przepis?');
        if(res){
            window.open('http://localhost:8080//app/recipe/delete?id=' + element.name,'resizable=yes');
        }
    })
})

 });