package jp.mgmgpkpk.moshimoshidemo

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jp.mgmgpkpk.moshimoshidemo.adapter.CardAdapter
import jp.mgmgpkpk.moshimoshidemo.databinding.ActivityMainBinding
import jp.mgmgpkpk.moshimoshidemo.model.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val json = resources.openRawResource(R.raw.cards)
                .bufferedReader()
                .use { it.readText() }
        Log.d(TAG, "json: $json")

        val moshi = Moshi.Builder()
                .add(CardAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()

        val dataType: Type = Types.newParameterizedType(Card::class.java)

        moshi.adapter<Card>(dataType).apply {
            this.fromJson(json)?.let {
                binding.card = it
                binding.textCard.text = it.showCardValue()
            }
        }
   }
}
