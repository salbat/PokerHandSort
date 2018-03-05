package com.appvisor.pokercards

fun main(args: Array<String>) {
    //var inputStream = "3C 3D 3S 9S 9D 3D 6D 7D TD QD"
    //println(inputStream.substring(0,14) + " --- " + inputStream.substring(15,29))
    //var p1Input = inputStream.substring(0,14);
    //var p2Input = inputStream.substring(15,29);
    var p1Score: Int = 0
    var p2Score: Int = 0

    while (true) {

        //println("Line: ${readLine()}")

        var inputStream = readLine() //"3C 3D 3S 9S 9D 3D 6D 7D TD QD"
        if (inputStream == null) {
            break
        }

        var p1Input = inputStream.substring(0, 14);
        var p2Input = inputStream.substring(15, 29);

        //println(inputStream.substring(0,14) + " --- " + inputStream.substring(15,29))

        var p1Hand: Hand = Hand(p1Input)
        var p2Hand: Hand = Hand(p2Input)


        p1Hand.calculateHand()
        p2Hand.calculateHand()

        /*
        var ranks = h.carRank()
        println(ranks +  " -> " + h.checkStraight(ranks.toList()))
        println("Check Flush: " + h.checkFlush())
        println(h.checkOnePairInMap(h.checkPair(ranks.toList())))
        println(h.checkTwoPairsInMap(h.checkPair(ranks.toList())))
        println(h.checkThreeOfAKindInMap(h.checkPair(ranks.toList())))
        println(h.checkFourOfAKindInMap(h.checkPair(ranks.toList())))
        println(h.checkFullHouseInMap(h.checkPair(ranks.toList())))
        println("Striaght-Flush: " + h.checkStraightFlush(ranks.toList()))
        println("Royal-Flush: " + h.checkRoyalFlushInMap(h.checkPair(ranks.toList())))
        */

        if (p1Hand.calculateHand().HV != 0 && p2Hand.calculateHand().HV != 0) {
            if (p1Hand.calculateHand().pokerHand.rank > p2Hand.calculateHand().pokerHand.rank)
                p1Score++
            else if (p1Hand.calculateHand().pokerHand.rank < p2Hand.calculateHand().pokerHand.rank)
                p2Score++
            else {
                //println("Tie RANK: " + p1Hand.calculateHand().pokerHand.rank + " <--> " + p2Hand.calculateHand().pokerHand.rank)
                if (p1Hand.calculateHand().HV > p2Hand.calculateHand().HV)
                    p1Score++
                else if (p1Hand.calculateHand().HV < p2Hand.calculateHand().HV)
                    p2Score++
                else {
                    if (p1Hand.getHighestCard() > p2Hand.getHighestCard()) p1Score++
                    else if (p1Hand.getHighestCard() < p2Hand.getHighestCard()) p2Score++
                    else {
                        if (p1Hand.get2ndHighestCard() > p2Hand.get2ndHighestCard()) p1Score++
                        else if (p1Hand.get2ndHighestCard() < p2Hand.get2ndHighestCard()) p2Score++
                        else {
                            if (p1Hand.get3rdHighestCard() > p2Hand.get3rdHighestCard()) p1Score++
                            else if (p1Hand.get3rdHighestCard() < p2Hand.get3rdHighestCard()) p2Score++
                            else {
                                if (p1Hand.get4thHighestCard() > p2Hand.get4thHighestCard()) p1Score++
                                else if (p1Hand.get4thHighestCard() < p2Hand.get4thHighestCard()) p2Score++
                                else {
                                    if (p1Hand.get5thHighestCard() > p2Hand.get5thHighestCard()) p1Score++
                                    else if (p1Hand.get5thHighestCard() < p2Hand.get5thHighestCard()) p2Score++
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    println("Player 1: $p1Score\nPlayer 2: $p2Score")
}