package com.example.movcompjagp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula (

    private val contexto: GRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyvlerView: RecyclerView
    ): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder> (){
    inner class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val actionButton: Button
        var numeroLikes = 0 //Esto sería mejor guardar en la clase Entrenador
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            actionButton = view.findViewById(R.id.btn_dar_like)
            actionButton.setOnClickListener { anadirLike() }
        }

        fun anadirLike(){
            numeroLikes = numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }

    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    //Setear los datos para la iteración
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.actionButton.text = "Like ${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }

    //Tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }


}