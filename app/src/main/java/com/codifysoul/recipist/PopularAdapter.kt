package com.codifysoul.recipist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codifysoul.recipist.databinding.PopularItemBinding

class PopularAdapter (var dataList:ArrayList<Recipe> ,var context:Context):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    inner class  ViewHolder(var binding: PopularItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = PopularItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding);
    }

    override fun getItemCount(): Int {
        return dataList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(dataList.get(position).img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text = dataList.get(position).tittle

        var time = dataList.get(position).ing.split("\n".toRegex()).dropLastWhile {  it.isEmpty()}.toTypedArray()
        holder.binding.popularTimeTxt.text =time.get(0);
    }
}