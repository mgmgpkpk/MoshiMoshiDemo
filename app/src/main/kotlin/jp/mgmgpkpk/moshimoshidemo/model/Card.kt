package jp.mgmgpkpk.moshimoshidemo.model

data class Card(
        var rank: String,
        var suit: Suit
) {

    fun showCardValue(): String {
        return "$rank, $suit"
    }
}

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}