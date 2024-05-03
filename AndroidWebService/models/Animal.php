<?php

class Animal{
    private $id,$name,$longevity,$env,$ate;

    /**
     * @param $id
     * @param $name
     * @param $longevity
     * @param $env
     * @param $ate
     */
    public function __construct($id, $name, $longevity, $env, $ate)
    {
        $this->id = $id;
        $this->name = $name;
        $this->longevity = $longevity;
        $this->env = $env;
        $this->ate = $ate;
    }


}