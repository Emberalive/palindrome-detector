//adds an action listener to prevent the form submition from reloading the page
document.addEventListener('DOMContentLoaded', function(){
    const form = document.querySelector("form");
    const boxDiv = document.querySelector(".box")
    form.addEventListener("submit", function(event){
        event.preventDefault();

        const palindrome = document.querySelector("#palindrome").value;

        //sending form data using the fetch API
        fetch('/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `palindrome=${encodeURIComponent(palindrome)}`
        })
            .then(response => response.json())
            .then(data => {
                //finding the element id with result
                const resultElement = document.querySelector('#result');
                if (data && data.message){
                    //setting the value of message
                    resultElement.innerText = data.message;
                    boxDiv.classList.add(data.backgroundColor); // Add the color class (green or red)
                    boxDiv.classList.remove(data.backgroundColor === "green" ? "red" : "green"); //changes the classlist of .box class depending on what the value of the data.background variable holds
                } else {

                }
                console.error('Error:', error);
            });
    });
});