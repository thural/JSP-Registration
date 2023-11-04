console.log("SCRIPT IS WORKING!!!")

let i = 0;

// variable to store parsed array from backend response
let questions;

// fetch and parse questions string, it performs GET method by default
const fetchQuestions = async () => {
    try {
        const data = await fetch('http://localhost:8080/quizAPI');
        const questions = await data.json();
        return questions;
    } catch (err) {
        console.log(err)
    }
}

// fetch and store the parsed questions array on page/script load
fetchQuestions().then(response => questions = response).then(() => {
    // add at least one question on page/script load
    addQuestion("question-container");
})

const postAnswers = async (answers) => {
    try {
        // post and await for the response
        const response = await fetch(
            "http://localhost:8080/quizAPI/answers",
            // fill request data
            {
                method: "post",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(answers)
            });

        // parse response and return response object/array
        const result = response.json();
        // on request failure log the status code
        if (!response.ok) {
            console.log("post failed with status code: ", response.status);
            return null
        } else return result;
    } catch (e) {
        console.error(e);
    }
}

// creat and add a new input/question field to the form
function addInput(formId) {
    // select the form
    const formElement = document.getElementById(formId);

    // create a question container
    const questionContainer = document.createElement("div");
    questionContainer.classList.add("radio-item-container");

    // add fieldset into the question container with an option container
    questionContainer.innerHTML = `<fieldset><legend>${questions[i].textContent}</legend></fieldset>`;

    // create an option container
    const optionContainer = document.createElement('div');
    optionContainer.innerHTML = `<div id="optionContainer${i}"></div>`;

    for (let j = 0; j < 4; j++) {
        // create an option field
        const newOptionField = document.createElement("div");

        newOptionField.innerHTML += `
        <div class="radio-item">
            <input
            type="radio"
            id="${questions[i].options[j]}"
            name="${questions[i].textContent}"
            value="${questions[i].options[j]}"
            checked />
            <label for="${questions[i].options[j]}">${questions[i].options[j]}</label>
        </div>`;

        // append option field to option container
        optionContainer.appendChild(newOptionField);
    }
    // append prepared containers/elements
    questionContainer.appendChild(optionContainer)
    formElement.appendChild(questionContainer)
    i++;
}

function addQuestion(containerId) {
    if (i < questions.length) addInput(containerId)
    console.log("number of questions: ", questions.length);
}

function collectValuesAndPost() {
    const answers = [];
    // get form data from the target form
    const formData = new FormData(document.getElementById("quiz-form"));

    // loop over the form data and push name and value pairs as objects
    formData.forEach((value, name) => {
        answers.push({"name": name, "value": value})
    });

    // post collected answers and log the response from the server
    postAnswers(answers).then(result => console.log(result));
}