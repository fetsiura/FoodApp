document.addEventListener("DOMContentLoaded", function () {

const confirmButton = document.querySelectorAll('#buttonToDelete')

    confirmButton.forEach(   element => {
        element.addEventListener('click', ele=> {
            console.log(element)
            ele.preventDefault()
        let res = confirm('Czy na pewno chcesz usunąć przepis z planu?');
        if(res){
            window.open('http://localhost:8080//app/recipe/plan/delete?id=' + element.name,'resizable=yes');
        }
    })
})


 });


