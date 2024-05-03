function goTopage3(){
    window.location.href = "fcourses.html"
}
function goTopage2(){
    window.location.href = "undergraduate.html"
}
function goTopage4(){
    window.location.href = "Hcourses.html"
}
function goTopage1(){
    window.location.href = "allcourses.html"
}
function goTopage6(){
    window.location.href = "createcourses.html"
}
document.getElementById("courseForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission

    var name = document.getElementById("name").value.trim();
    var description = document.getElementById("description").value.trim();
    var isValid = true;

    // Validation
    if (name === "") {
        document.getElementById("nameError").innerText = "Name is required";
        isValid = false;
    } else {
        document.getElementById("nameError").innerText = "";
    }

    if (description === "") {
        document.getElementById("descriptionError").innerText = "Description is required";
        isValid = false;
    } else {
        document.getElementById("descriptionError").innerText = "";
    }

    if (!isValid) {
        return;
    }

    // Form data
    var formData = {
        name: name,
        description: description
    };

    // Send POST request to backend
    fetch("/api/courses", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                alert("Course created successfully!");
                document.getElementById("courseForm").reset(); // Clear form
            } else {
                alert("Failed to create course. Please try again.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("An error occurred. Please try again later.");
        });
});