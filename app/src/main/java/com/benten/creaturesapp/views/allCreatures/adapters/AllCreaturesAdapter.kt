package com.benten.creaturesapp.views.allCreatures.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.benten.creaturesapp.databinding.LayoutCreatureItemBinding
import com.benten.creaturesapp.model.Creature
import com.benten.creaturesapp.views.addCreature.data.AddCreatureDataModel

class AllCreaturesAdapter : RecyclerView.Adapter<AllCreaturesAdapter.AllCreaturesViewHolder>() {

    private val creatures = mutableListOf<Creature>()
    private var clickListener: ((AddCreatureDataModel) -> Unit)? = null

    override

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCreaturesViewHolder {
        return AllCreaturesViewHolder(
            LayoutCreatureItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllCreaturesViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount(): Int {
        return creatures.size
    }

    fun setOnClickListener(clickListener: (AddCreatureDataModel) -> Unit) {
        this.clickListener = clickListener
    }

    fun updateAll(list: List<Creature>) {
        creatures.clear()
        creatures.addAll(list)
        notifyDataSetChanged()
    }

    inner class AllCreaturesViewHolder(private val binding: LayoutCreatureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(creature: Creature) {
            binding.avatarListItem.setImageResource(creature.drawable)
            binding.name.text = creature.name
            binding.hitPoints.text = creature.hitPoints.toString()
            ViewCompat.setTransitionName(binding.avatarListItem, "bravo_${creature.hitPoints}")
            binding.root.setOnClickListener {
                clickListener?.invoke(

                    AddCreatureDataModel(
                        creature.drawable,
                        creature.name,
                        creature.hitPoints
                    )
                )


            }
        }
    }
}
