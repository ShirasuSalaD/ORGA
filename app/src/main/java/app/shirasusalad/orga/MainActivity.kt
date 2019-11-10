package app.shirasusalad.orga

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mP1 = MediaPlayer.create(this, R.raw.kibounohana_ani)
        val mP2 = MediaPlayer.create(this, R.raw.kibounohana_sabi)
        val mP3 = MediaPlayer.create(this, R.raw.dontstop)


        //初期設定
        mP1.seekTo(0)
        mP2.seekTo(0)
        mP3.seekTo(0)
        image_orga.setImageResource(R.drawable.orga_normal)
        image_orga.imageAlpha = 255
        var life = Random.nextInt(25, 50)
        text_orga_life.text = life.toString()

        //ライフ減っていく（カウントアプリのマイナス）
        image_orga.setOnClickListener {
            life -= 1
            text_orga_life.text = life.toString()

            //ライフに合わせて画像切り替え
            when {
                life == 25 -> {
                    mP1.start()
                }
                life in 1..10 -> {
                    image_orga.setImageResource(R.drawable.orga_yabai)
                }
                life in -50..0 -> {
                    mP1.stop()
                    mP2.start()
                    image_orga.setImageResource(R.drawable.orga_death)
                    image_orga.imageAlpha = 255 + life * 5

                    when (life) {
                        -25 -> {
                            mP3.start()
                        }
                    }
                }
                life == -50 -> {
                    image_orga.imageAlpha = 0
                }
                life == -70 -> {
                    //初期化
                    mP1.stop()
                    mP2.stop()
                    mP3.stop()
                    reload()
                }
            }
//            ifでもいいけどwhenのほうがきれい
//            if (life > 10) {
//                image_orga.setImageResource(R.drawable.orga_normal)
//            } else if (life in 1..10) {
//                image_orga.setImageResource(R.drawable.orga_yabai)
//            } else if (life <= 0) {
//                image_orga.setImageResource(R.drawable.orga_death)
//                image_orga.alpha = alpha
//            }

            text_orga_life.setOnClickListener{
                mP1.stop()
                mP2.stop()
                mP3.stop()
                reload()
            }
        }


    }

    private fun reload() {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()

        overridePendingTransition(0, 0)
        startActivity(intent)
    }

}