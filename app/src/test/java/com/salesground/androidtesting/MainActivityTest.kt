package com.salesground.androidtesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(maxSdk = 28, minSdk = 28)
class MainActivityTest {
    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun createFile_with_null_filename_returns_null(){
        val mainActivity = MainActivity()

        val createdFile = mainActivity.createFile(context, null)
        assertEquals(createdFile, null)
    }

    @Test
    fun createFile_with_empty_string_returns_parent_file(){
        val mainActivity = MainActivity()
        val createdFile = mainActivity.createFile(context, "")
        assertEquals(createdFile?.name, "external-files")
    }


}