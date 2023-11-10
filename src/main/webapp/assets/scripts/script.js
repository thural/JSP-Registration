console.log("SCRIPT IS WORKING!!!")

let i = 0;

// variable to store parsed array from backend response
let questions;

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

    // insert described fieldset element into question container
    questionContainer.innerHTML = `<fieldset><legend>${questions[i].textContent}</legend></fieldset>`;

    // create an option container
    const optionContainer = document.createElement('div');
    optionContainer.innerHTML = `<div id="optionContainer${i}"></div>`;

    for (let j = 0; j < 4; j++) {
        // create an option field
        const newOptionField = document.createElement("div");

        // insert the described div element into the field
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
    if (i < questions.length) addInput(containerId);
}

// build and append a popup using the result
function showResult(result) {
    // show/draw an overlay element
    const overlay = document.getElementById("overlay");
    overlay.style.display = "block";

    //  create a container div for popup
    const popup = document.createElement("div");
    popup.classList.add("popup");

    // add a h3 tag element to the popup
    popup.innerHTML += "<h3>Quiz Results:</h3>";

    // create a result container
    const resultContainer =  document.createElement("div");
    resultContainer.classList.add("result-container");
    let totalQuestions = result.length;
    let correctAnswers = totalQuestions;

    // append an element for each object of the result array
    result.forEach(questionObject => {
        const thisResult = document.createElement("div");
        thisResult.classList.add("result");

        if(questionObject["answer"] !== questionObject["userAnswer"]){

            for (let [key, value] of Object.entries(questionObject)) {

                switch (key){
                    case "questionName":
                        key = "";
                        break;
                    case "answer":
                        key = "correct answer: ";
                        break;
                    case "userAnswer":
                        key = "your answer: "
                        break
                    default:
                        console.log("unknown question field");
                }

                // create a result field and apply red color for wrong answers
                const textColor = key === "your answer" ? "red" : "black";
                thisResult.innerHTML += `<p style="color:${textColor};">${key + value}</p>`;
                // append result field to the container
                resultContainer.appendChild(thisResult);
                popup.appendChild(resultContainer);
            }
            correctAnswers--;
        }
    });

    // create bottom section
    const bottomSection = document.createElement("div");
    bottomSection.classList.add("bottom-section");

    // create a score field
    const scoreField = document.createElement("h4");
    scoreField.textContent = "Total Score: " + `${correctAnswers}` + "/" + `${totalQuestions}`;
    bottomSection.appendChild(scoreField);

    // create a restart button
    bottomSection.innerHTML +=
        `<button type="button" onclick="location.href='http://localhost:8080/quiz';">new quiz</button>`

    // append body bottom section element to popup
    popup.appendChild(bottomSection);

    // append popup to html body (root) element
    document.body.appendChild(popup);
}

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

// post form data and display popup using response data
function collectValuesAndPost() {
    const answers = [];

    // get form data from the target form
    const formData = new FormData(document.getElementById("quiz-form"));

    // loop over the form data and push name and value pairs as objects
    formData.forEach((value, name) => {
        // get the correct answer from questions data for current question
        const correctAnswer = questions.find(question => question["textContent"] === name)["answer"];
        //construct and push current answer object into the array
        answers.push({"name": name, "value": value, "correctAnswer": correctAnswer});
    });

    // post collected form values (answers) and then use the response data (result) to display the popup
    postAnswers(answers).then(result => showResult(result));
}