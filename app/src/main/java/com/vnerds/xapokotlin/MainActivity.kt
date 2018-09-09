package com.vnerds.xapokotlin

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextThemeWrapper
import android.view.View
import android.view.Window
import android.widget.ProgressBar

import com.vnerds.xapokotlin.adapters.MyAdapter
import com.vnerds.xapokotlin.api.RestClient
import com.vnerds.xapokotlin.app.XapoApp
import com.vnerds.xapokotlin.objects.Git_Object
import com.vnerds.xapokotlin.objects.ItemsItem

import kotlinx.android.synthetic.main.activity_main.*

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var mAdapter: MyAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private var alertDialog: Dialog? = null
    private var child: View? = null

    val currentDate: String
        get() {
            val calendar = Calendar.getInstance()
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val date = calendar.time
            return formatter.format(date)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager



        mAdapter = MyAdapter( ArrayList<ItemsItem>())
        recyclerView.adapter = mAdapter

        initDialog()
        callGetTopRepository()

    }

    fun initDialog() {
        child = layoutInflater.inflate(R.layout.custom_progress_bar, null)
        val progressBar = child!!.findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar.indeterminateDrawable.setColorFilter(Color.WHITE,
                android.graphics.PorterDuff.Mode.SRC_IN)


        alertDialog = Dialog(ContextThemeWrapper(this, R.style.alertDialogCustom))
        alertDialog!!.setCancelable(false)
        alertDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog!!.setContentView(child!!)
        alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showDialog() {
        alertDialog!!.show()
    }

    fun dismissDialog() {
        alertDialog!!.hide()
    }

    fun callGetTopRepository() {
        showDialog()
        val url = RestClient.search + currentDate
        val mCallGetTopRepo = XapoApp.restClient!!.applicationServices.getTopRepository(url)
        mCallGetTopRepo.enqueue(object : Callback<Git_Object> {
            override fun onResponse(call: Call<Git_Object>, response: Response<Git_Object>) {
                dismissDialog()
                val git_object:Git_Object? = response.body()
                if (git_object != null) {
                    mAdapter!!.updateAdapter(git_object.items)
                }
            }

            override fun onFailure(call: Call<Git_Object>, t: Throwable) {

            }
        })

    }

}
