package com.vnerds.xapokotlin.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.vnerds.xapokotlin.R
import com.vnerds.xapokotlin.WebViewActivity
import com.vnerds.xapokotlin.objects.ItemsItem

import kotlinx.android.synthetic.main.activity_recyclerview_listitem.view.*

import java.util.ArrayList

class MyAdapter(myDataset: ArrayList<ItemsItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var mDataset: List<ItemsItem>? = null

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var mTextView: TextView
        var mTextDesc: TextView
        var mOwnerName: TextView
        var mImgAvatar: ImageView

        init {
            mTextView = v.tvTitle
            mOwnerName = v.tvOwner
            mTextDesc = v.tvDesc
            mImgAvatar = v .imgAvatar
        }
    }


    init {
        mDataset = myDataset
    }

    fun updateAdapter(myDataset: List<ItemsItem>?) {
        mDataset = myDataset
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_recyclerview_listitem, parent, false) as View

        return MyViewHolder(v)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemsBeans = mDataset!![position]
        holder.mTextView.text = itemsBeans.name
        holder.mTextDesc.text = itemsBeans.description

        holder.mOwnerName.text = itemsBeans.owner!!.login
        Glide.with(holder.mTextView.context).load(itemsBeans.owner.avatarUrl).into(holder.mImgAvatar)
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, WebViewActivity::class.java)
            intent.putExtra("title", itemsBeans.name)
            intent.putExtra("url", itemsBeans.htmlUrl)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mDataset!!.size
    }
}