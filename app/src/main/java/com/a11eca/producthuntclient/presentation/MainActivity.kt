package com.a11eca.producthuntclient.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.presentation.fragment.CollectionsFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.beginTransaction()
        .add(R.id.content_root, CollectionsFragment())
        .commit()
  }
}
