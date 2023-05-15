package com.example.songs

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var sizeButton: Button
    private lateinit var scrollButton: ImageButton
    private lateinit var scrollView: ScrollView
    private lateinit var speedButton: Button
    private lateinit var backgroundButton: ImageButton
    private val lyricsList = mutableListOf<String>()
    private val fontList = listOf(18, 24, 32)
    private var selectedFont = 0
    private var isScrolling = false
    private lateinit var scrollAnimation: ObjectAnimator
    private val speedList = listOf(0.5, 1.0, 2.0)
    private var selectedSpeed = 1;
    private val colorList = listOf(R.color.white, R.color.grey1, R.color.grey2)
    private var selectedColor = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        sizeButton = findViewById(R.id.size_button)
        scrollButton = findViewById(R.id.scroll_button)
        scrollView = findViewById(R.id.scrollView)
        speedButton = findViewById(R.id.speeed_button)
        backgroundButton = findViewById(R.id.bacground_button)

        lyricsList.add(getString(R.string.song1))

        loadSong(0)
        setFontSize(fontList[0])
        setBackgroundColor()

        sizeButton.setOnClickListener{onChangeSizeButtonClick()}
        scrollButton.setOnClickListener {
            onScrollButtonClick()
        }

        speedButton.setOnClickListener{onSpeedButtonClick()}
        backgroundButton.setOnClickListener{onColorButtonClick()}

    }

    private fun loadSong(id: Int)
    {
        textView.text = lyricsList[id]
    }

    private fun onChangeSizeButtonClick()
    {
        selectedFont = (selectedFont + 1) % fontList.size
        setFontSize(fontList[selectedFont])
    }

    private fun setFontSize(size: Int)
    {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
    }

    private fun onScrollButtonClick()
    {
        isScrolling = !isScrolling
        if (isScrolling)
        {
            scrollAnimation = ObjectAnimator.ofInt(scrollView, "scrollY", scrollView.getChildAt(0).height)
            scrollAnimation.setDuration((1 / speedList[selectedSpeed] * 1 * 60 * 1000).toLong())
            scrollAnimation.start()
        }
        else
        {
            scrollAnimation.cancel()
        }
    }

    private fun onSpeedButtonClick()
    {
        selectedSpeed = (selectedSpeed + 1) % speedList.size
        speedButton.text = speedList[selectedSpeed].toString()
    }

    private fun onColorButtonClick()
    {
        selectedColor = (selectedColor + 1) % colorList.size
        setBackgroundColor()
    }

    private fun setBackgroundColor()
    {
        textView.setBackgroundResource(colorList[selectedColor])
    }
}