package com.masai.sainath.itunesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.itunesapi.remote.responses.ResultModel
import com.bumptech.glide.Glide
import com.masai.sainath.itunesapi.R
import com.masai.sainath.itunesapi.databinding.ItemLayoutBinding

class ItunesAdapter(
    private val resultList: List<ResultModel>
) : RecyclerView.Adapter<ItunesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout, parent, false
        )
        return ItunesViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ItunesViewHolder, position: Int) {
        val resultModel = resultList[position]
        holder.onBind(resultModel)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

}

class ItunesViewHolder(
    private val itemLayoutBinding: ItemLayoutBinding,
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBind(resultModel: ResultModel) {
        itemLayoutBinding.resultModel = resultModel
        Glide.with(itemLayoutBinding.artistImage).load(resultModel.artworkUrl100)
            .into(itemLayoutBinding.artistImage)
    }

}