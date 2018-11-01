package jp.mgmgpkpk.moshimoshidemo.adapter

import jp.mgmgpkpk.moshimoshidemo.model.Card
import jp.mgmgpkpk.moshimoshidemo.model.Suit
import com.squareup.moshi.*

class CardAdapter {

    @ToJson fun toJson(card: Card): String {
        return card.rank + card.suit.toString().substring(0, 1)
    }

    @FromJson fun fromJson(json: String): Card? {
        if (json.isEmpty()) return null

        val rank = json[0].toString()
        return when (json[1]) {
            'C' -> Card(rank, Suit.CLUBS)
            'D' -> Card(rank, Suit.DIAMONDS)
            'H' -> Card(rank, Suit.HEARTS)
            'S' -> Card(rank, Suit.SPADES)
            else -> throw JsonDataException("unknown suit: $json")
        }
    }
}