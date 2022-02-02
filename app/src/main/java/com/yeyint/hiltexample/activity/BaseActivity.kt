package com.yeyint.hiltexample.activity

import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {
    protected fun setUpToolbar(toolbar: Toolbar, isChild: Boolean) {
        setSupportActionBar(toolbar)

        if (isChild) {
            if (supportActionBar != null) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    protected fun setUpToolbarText(toolbar_text: TextView, textValue: String) {
        toolbar_text.text = textValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}