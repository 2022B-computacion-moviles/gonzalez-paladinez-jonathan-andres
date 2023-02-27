package com.example.deber_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        val listaMensajes = arrayListOf<Message>()
        listaMensajes.add(Message( "Hola, ¿cómo estás?", "10:40"))
        listaMensajes.add(Message("Hola, bien gracias ¿y tú?","10:41"))
        listaMensajes.add(Message("¡Hola! ¿Cómo estás?","08:30"))
        listaMensajes.add(Message("¿Quieres salir a caminar esta tarde?","14:20"))
        listaMensajes.add(Message("No puedo esperar a ver la película esta noche","19:15"))
        listaMensajes.add(Message("¿Te gustaría unirte a mi equipo para el proyecto?","11:50"))
        listaMensajes.add(Message("Feliz cumpleaños!","00:05"))
        listaMensajes.add(Message("¿Qué planes tienes para el fin de semana?","09:00"))
        listaMensajes.add(Message("¿Quieres ir al concierto el próximo sábado?","17:30"))
        listaMensajes.add(Message("¿Podrías revisar mi presentación antes de la reunión?","12:45"))
        listaMensajes.add(Message("No puedo creer que hayamos ganado el juego","22:15"))
        listaMensajes.add(Message("¡Felicidades por tu nuevo trabajo!","10:00"))

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_chat)
        inicializarRecyclerView(listaMensajes, recyclerView)

    }

    private fun inicializarRecyclerView(listaMensajes: ArrayList<Message>, recyclerView: RecyclerView) {
        val adaptador = MessageAdapter(listaMensajes,this, recyclerView)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }

}