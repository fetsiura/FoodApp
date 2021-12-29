document.addEventListener("DOMContentLoaded", function () {

    const confirmButton = document.querySelectorAll('#deletePlan')

    confirmButton.forEach(   element => {
        element.addEventListener('click', ele=> {
            console.log('click')
            ele.preventDefault()
            let res = confirm('Czy na pewno chcesz usunąć plan?');
            if(res){
                window.open('http://localhost:8080//app/plan/delete?id=' + element.name,'resizable=yes');
            }
        })
    })

});