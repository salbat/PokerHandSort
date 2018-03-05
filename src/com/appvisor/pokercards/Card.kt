package com.appvisor.pokercards

public class Card (str:String) {
    var value: Char
    var suit: Char
    //var cardValue : Int = null

    init {
        value = str[0]
        suit = str[1]
    }

    override fun toString(): String {
        return "$value$suit"
    }

    fun getCardValue() : Int {
        //println("Value is : $value")
        if(value == 'T') return 10
        else if(value == 'J') return 11
        else if(value == 'Q') return 12
        else if(value == 'K') return 13
        else if(value == 'A') return 14
        else return value.toString().toInt()

    }

}