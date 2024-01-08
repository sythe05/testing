let questions = [
    {
        no: 1,
        question: "What role is of Deadlock?",
        answer: "Sentinel",
        options: [
            "Duelist",
            "Initiator",
            "Controller",
            "Sentinel"
        ]
    },
    {
        no: 2,
        question: "What role is of Astra?",
        answer: "Controller",
        options: [
            "Duelist",
            "Initiator",
            "Controller",
            "Sentinel"
        ]
    },
    {
        no: 3,
        question: "What role is of Yoru?",
        answer: "Duelist",
        options: [
            "Duelist",
            "Initiator",
            "Controller",
            "Sentinel"
        ]
    },
    {
        no: 4,
        question: "What role is of Breach?",
        answer: "Initiator",
        options: [
            "Duelist",
            "Initiator",
            "Controller",
            "Sentinel"
        ]
    },
    {
        no: 5,
        question: "What role is of Harbor?",
        answer: "Controller",
        options: [
            "Duelist",
            "Initiator",
            "Controller",
            "Sentinel"
        ]
    }

]

const resultBox = document.querySelector('.result-box');
let questionCount = 0;
let questionNumb = 1;
let userScore = 0;

const quizBox = document.querySelector('.quiz-box');
quizBox.classList.add('active');

const nextBtn = document.querySelector('.next-btn');
nextBtn.onclick = () => {
    if (questionCount<questions.length-1) {
        questionCount++;
        showQuestions(questionCount);

        questionNumb++;
        questionCounter(questionNumb);

        nextBtn.classList.remove('active');
    }
    else {
        showResultBox();
    }
}

const optionList = document.querySelector('.option-list');
function showQuestions(index) {
    const questionText = document.querySelector('.question-text');
    questionText.textContent = `${questions[index].no}. ${questions[index].question}`;

    let optionTag = `<div class="option"><span>${questions[index].options[0]}</span></div>
    <div class="option"><span>${questions[index].options[1]}</span></div>
    <div class="option"><span>${questions[index].options[2]}</span></div>
    <div class="option"><span>${questions[index].options[3]}</span></div>`;

    optionList.innerHTML = optionTag;
    const option = document.querySelectorAll('.option');
    for (let i = 0; i <option.length; i++) {
        option[i].setAttribute('onclick', 'optionSelected(this)');
    }
}

function optionSelected(answer) {
    let userAnswer = answer.textContent;
    let correctAnswer = questions[questionCount].answer;
    let allOptions = optionList.children.length;
    if(userAnswer == correctAnswer) {
        answer.classList.add('correct');
        userScore += 1;
        headerScore();
    }
    else {
        answer.classList.add('incorrect');
        for (let i = 0; i<allOptions; i++) {
            if(optionList.children[i].textContent == correctAnswer) {
                optionList.children[i].setAttribute('class', 'option correct');
            }
        }
    }

    for (let i = 0; i<allOptions; i++) {
        optionList.children[i].classList.add('disabled');
    }

    nextBtn.classList.add('active');
}

function questionCounter(index) {
    const questionTotal = document.querySelector('.question-total');
    questionTotal.textContent = `${index} of ${questions.length} Questions`;
}

function headerScore() {
    const headerScoreText = document.querySelector('.header-score');
    headerScoreText.textContent= `Score: ${userScore} / ${questions.length}`;
}

showQuestions(0);
questionCounter(1);
headerScore();

function showResultBox() {
    quizBox.classList.remove('active');
    resultBox.classList.add('active');
    const scoreText = document.querySelector('.score-text');
    scoreText.textContent = `Your Score is ${userScore} out of ${questions.length}`;
    const circularProgress = document.querySelector('.circular-progress');
    const progressValue = document.querySelector('.progress-value');
    let progressStartValue = -1;
    let progressEndValue = (userScore / questions.length) * 100;
    let speed = 20;

    let progress = setInterval(() => {
        progressStartValue++;
        // console.log(progressStartValue);
        progressValue.textContent = `${progressStartValue}%`;
        circularProgress.style.background = `conic-gradient(#27bd29 ${progressStartValue * 3.6}deg, rgba(48, 173, 98, 0.384) 0deg)`;

        if (progressStartValue == progressEndValue) {
            clearInterval(progress);
        }
    }, speed);
}