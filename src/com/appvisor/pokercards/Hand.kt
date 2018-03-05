package com.appvisor.pokercards

import java.util.*

data class CardVals(val pokerHand: PokerHand, val HV: Int)

public class Hand(_str:String) {

    val CARDSTR = "--23456789TJQKA"
    val CARDSUIT = "DHSC"
    var splitStr = _str.split(" ")//.iterator()
    var cards: ArrayList<Card> = arrayListOf()
    lateinit var pokerHand: PokerHand

    init {
        for (i in splitStr) {
            cards.add(Card(i))
        }
    }

    fun getCard(i: Int): Card {
        //println(cards[i].getCardValue())
        return cards[i]
    }

    //Calculate the SORTED ranks of each Hand
    fun cardRankSorted(): List<Int> {
        var ranks: ArrayList<Int> = arrayListOf()
        for (i in this.cards) {
            //println("I: " + i.toString())
            ranks.add(CARDSTR.indexOf(i.toString()[0]))
        }
        return ranks.sortedByDescending { it }
    }

    //Calculate the ranks of each Hand
    fun cardRank(): List<Int> {
        var ranks: ArrayList<Int> = arrayListOf()
        for (i in this.cards) {
            ranks.add(CARDSTR.indexOf(i.toString()[0]))
        }
        return ranks
    }

    //Generate a map of each item in the list and its occurrences
    fun checkPair(rank: List<Int>): Map<Int, Int> {
        return rank.groupingBy { it }.eachCount()
    }

    //#1:  Check Straight Flush
    fun checkStraightFlush(rank: List<Int>): Boolean {
        return checkFlush() && checkStraight(rank)
    }

    //#2:  Check for "straight" - 4 consecutive values
    fun checkStraight(rank: List<Int>): Boolean {
        return ((rank.toSet().size == 5) && (4 == (rank.max()!! - rank.min()!!)))
    }

    //#3:  Check for "Flush" - 4 consecutive values
    fun checkFlush(): Boolean {
        var suits: MutableSet<Char> = mutableSetOf()
        for (i in this.cards) {
            suits.add(i.toString()[1])
        }
        return suits.toSet().size == 1
    }

    //#4:  Check the number of pairs in the map's values
    fun checkOnePairInMap(rankMap: Map<Int, Int>): Boolean {
        //println(rankMap)
        //OLD: return rankMap.containsValue(2)
        var sortedMap = rankMap.toSortedMap()
        //println(sortedMap.values.sorted())
        return sortedMap.values.sorted().toIntArray() contentEquals intArrayOf(1,1,1,2)
    }

    fun returnOnePairInMap(rankMap: Map<Int, Int>) : Int {
        for ((index, cardVal) in rankMap.values.withIndex()) {
            if(cardVal == 2) {
                //println("Pair of $rankMap with: " + rankMap.keys.toIntArray()[index])
                return rankMap.keys.toIntArray()[index]
            }
        }
        return -1
    }

    //#5:  Check 2 Pairs
    fun checkTwoPairsInMap(rankMap:Map<Int, Int>) : Boolean {
        var sortedMap = rankMap.toSortedMap()
        //println(sortedMap.values.sorted())
        return sortedMap.values.sorted().toIntArray() contentEquals intArrayOf(1,2,2)
                /*||
                sortedMap.values.toIntArray() contentEquals intArrayOf(1,2,2) ||
                sortedMap.values.toIntArray() contentEquals intArrayOf(2,1,2)*/
    }

    //#6:  Check 3 of a kind
    fun checkThreeOfAKindInMap(rankMap:Map<Int, Int>) : Boolean {
        var sortedMap = rankMap.toSortedMap()
        //println(sortedMap.values.sorted())
        return sortedMap.values.sorted().toIntArray() contentEquals intArrayOf(1,1,3) /*||
                sortedMap.values.toIntArray() contentEquals intArrayOf(3,1,1) ||
                sortedMap.values.toIntArray() contentEquals intArrayOf(1,1,3) ||
                sortedMap.values.toIntArray() contentEquals intArrayOf(3,1) ||
                sortedMap.values.toIntArray() contentEquals intArrayOf(1,3)*/
    }

    fun returnThreeOfAKindInMap(rankMap: Map<Int, Int>) : Int {
        for ((index, cardVal) in rankMap.values.withIndex()) {
            if(cardVal == 3) {
                //println("3ofAkind of $rankMap with: " + rankMap.keys.toIntArray()[index])
                return rankMap.keys.toIntArray()[index]
            }
        }
        return -1
    }

    //#7:  Check 4 of a kind
    fun checkFourOfAKindInMap(rankMap:Map<Int, Int>) : Boolean {
        var sortedMap = rankMap.toSortedMap()
        //print(sortedMap.values.toSortedSet())
        return sortedMap.values.sorted().toIntArray() contentEquals intArrayOf(1,4) /*||
                sortedMap.values.toIntArray() contentEquals intArrayOf(1,4)*/
    }
    fun returnFourOfAKindInMap(rankMap: Map<Int, Int>) : Int {
        for ((index, cardVal) in rankMap.values.withIndex()) {
            if(cardVal == 4) {
                //println("4ofAkind of $rankMap with: " + rankMap.keys.toIntArray()[index])
                return rankMap.keys.toIntArray()[index]
            }
        }
        return -1
    }

    //#8:  Check full-house
    fun checkFullHouseInMap(rankMap:Map<Int, Int>) : Boolean {
        var sortedMap = rankMap.toSortedMap()
        //print("Fullhouse: " + sortedMap.values.toSortedSet())
        return sortedMap.values.sorted().toIntArray() contentEquals intArrayOf(2,3)
                //|| sortedMap.values.toIntArray() contentEquals intArrayOf(3,2)
    }
    fun returnFullHouseInMap(rankMap: Map<Int, Int>) : Int {
        for ((index, cardVal) in rankMap.values.withIndex()) {
            if(cardVal == 3) {
                //println("FULLHOUSE of $rankMap with: " + rankMap.keys.toIntArray()[index])
                return rankMap.keys.toIntArray()[index]
            }
        }
        return -1
    }


    //#9:  Check Royal Flush
    fun checkRoyalFlushInMap(rankMap:Map<Int, Int>) : Boolean {
        var sortedMap = rankMap.toSortedMap()
        //println("Royal Flush: " +  sortedMap.keys)
        return checkFlush() && sortedMap.keys.sorted().toIntArray() contentEquals intArrayOf(10,11,12,13,14)
    }

    //Calculate and evaluate the hand's cards
    fun calculateHand(): CardVals {
        val ranks = this.cardRank()
        val pair = this.checkPair(ranks.toList())


        //println(ranks)
        //println(pair)

        /*
        if(checkRoyalFlushInMap(pair)) pokerHand = PokerHand.ROYALFLUSH
        else if(checkStraightFlush(ranks)) pokerHand = PokerHand.STRAIGHTFLUSH
        else if(checkFourOfAKindInMap(pair)) pokerHand = PokerHand.FOUROFAKIND
        else if(checkFullHouseInMap(pair)) pokerHand = PokerHand.FULLHOUSE
        else if(checkFlush()) pokerHand = PokerHand.FLUSH
        else if(checkStraight(ranks)) pokerHand = PokerHand.STRAIGHT
        else if(checkThreeOfAKindInMap(pair)) pokerHand = PokerHand.THREEOFAKIND
        else if(checkTwoPairsInMap(pair)) pokerHand = PokerHand.TWOPAIRS
        else if(checkOnePairInMap(pair)) pokerHand = PokerHand.PAIR
        else pokerHand = PokerHand.HIGHCARD

        return pokerHand*/
        var cVals : CardVals

        if(checkRoyalFlushInMap(pair)) cVals = CardVals(PokerHand.ROYALFLUSH,10)
        else if(checkStraightFlush(ranks)) cVals = CardVals(PokerHand.STRAIGHTFLUSH,9)
        else if(checkFourOfAKindInMap(pair)) cVals = CardVals(PokerHand.FOUROFAKIND,returnFourOfAKindInMap(pair))
        else if(checkFullHouseInMap(pair)) cVals = CardVals(PokerHand.FULLHOUSE,returnFullHouseInMap(pair))
        else if(checkFlush()) cVals = CardVals(PokerHand.FLUSH,6)
        else if(checkStraight(ranks)) cVals = CardVals(PokerHand.STRAIGHT,5)
        else if(checkThreeOfAKindInMap(pair)) cVals = CardVals(PokerHand.THREEOFAKIND,returnThreeOfAKindInMap(pair))
        else if(checkTwoPairsInMap(pair)) cVals = CardVals(PokerHand.TWOPAIRS,3)
        else if(checkOnePairInMap(pair)) cVals = CardVals(PokerHand.PAIR,returnOnePairInMap(pair))
        else cVals = CardVals(PokerHand.HIGHCARD,getHighestCard())

        return cVals

    }

    fun getHighestCard() : Int {
        val ranks = this.cardRankSorted()
        //println(ranks[0])
        return ranks[0]
    }

    fun get2ndHighestCard() : Int {
        val ranks = this.cardRankSorted()
        //println(ranks + " --> " + ranks[0])
        return ranks[1]
    }

    fun get3rdHighestCard() : Int {
        val ranks = this.cardRankSorted()
        //println(ranks + " --> " + ranks[0])
        return ranks[2]
    }

    fun get4thHighestCard() : Int {
        val ranks = this.cardRankSorted()
        //println(ranks + " --> " + ranks[0])
        return ranks[4]
    }

    fun get5thHighestCard() : Int {
        val ranks = this.cardRankSorted()
        //println(ranks + " --> " + ranks[0])
        return ranks[5]
    }
}