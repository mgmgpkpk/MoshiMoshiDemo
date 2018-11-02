package jp.mgmgpkpk.moshimoshidemo

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jp.mgmgpkpk.moshimoshidemo.databinding.ActivityMainBinding
import jp.mgmgpkpk.moshimoshidemo.model.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

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
                .add(KotlinJsonAdapterFactory())
                .build()

        moshi.adapter(Card::class.java).apply {
            this.fromJson(json)?.let {
                binding.card = it
                binding.textCard.text = it.showCardValue()
            }
        }
   }
}
