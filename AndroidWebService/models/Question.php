<?php

// Định nghĩa lớp Question
class Question {
    public $id;
    public $question;
    public $ideaA;
    public $ideaB;
    public $ideaC;
    public $ideaD;
    public $answer;

    public function __construct($id, $question, $ideaA, $ideaB, $ideaC, $ideaD, $answer) {
        $this->id = $id;
        $this->question = $question;
        $this->ideaA = $ideaA;
        $this->ideaB = $ideaB;
        $this->ideaC = $ideaC;
        $this->ideaD = $ideaD;
        $this->answer = $answer;
    }
}


?>