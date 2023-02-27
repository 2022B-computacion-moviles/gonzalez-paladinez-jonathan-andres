package com.example.deber_02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //chatRecyclerView = findViewById(R.id.recycler_chats)

        val listaChats = arrayListOf<ChatMessage>()
        listaChats.add(ChatMessage("Alice", "Hola, ¿cómo estás?", "10:40", "3"))
        listaChats.add(ChatMessage("Alice", "Hola, ¿cómo estás?", "10:40", "3"))
        listaChats.add(ChatMessage("Alice", "Bien también, gracias","10:42", "3"))
        listaChats.add(ChatMessage("Bob", "Qué bueno","10:43", "3"))
        listaChats.add(ChatMessage("Bob", "Hola, ¿cómo estás?", "10:40", "1"))
        listaChats.add(ChatMessage("Charlie", "Hola Bob, estoy bien, ¿y tú?", "10:42", "2"))
        listaChats.add(ChatMessage("Bob", "Estoy bien, gracias por preguntar", "10:45", "3"))
        listaChats.add(ChatMessage("Alice", "¿Qué planes tienes para hoy?", "11:00", "4"))
        listaChats.add(ChatMessage("Charlie", "Nada en particular, ¿tú?", "11:05", "5"))
        listaChats.add(ChatMessage("Alice", "Voy a ir al cine con unos amigos, ¿te gustaría unirte?", "11:10", "6"))
        listaChats.add(ChatMessage("Charlie", "¡Claro! ¿A qué hora es?", "11:15", "7"))
        listaChats.add(ChatMessage("Alice", "A las 7 de la tarde en el cine del centro comercial", "11:20", "8"))
        listaChats.add(ChatMessage("Charlie", "Genial, allí estaré", "11:25", "9"))
        listaChats.add(ChatMessage("Bob", "¿Qué opinas de la nueva película de ciencia ficción?", "12:00", "10"))
        listaChats.add(ChatMessage("Alice", "No la he visto aún, ¿vale la pena?", "12:05", "11"))
        listaChats.add(ChatMessage("Bob", "Sí, definitivamente vale la pena", "12:10", "12"))
        listaChats.add(ChatMessage("Alice", "Entonces la veré esta noche", "12:15", "13"))
        listaChats.add(ChatMessage("Bob", "¡Genial! Después me cuentas qué te pareció", "12:20", "14"))
        listaChats.add(ChatMessage("Charlie", "¿Qué planes tienes para el fin de semana?", "13:00", "15"))
        listaChats.add(ChatMessage("Bob", "Pienso ir de excursión con unos amigos", "13:05", "16"))
        listaChats.add(ChatMessage("Alice", "Yo voy a visitar a mi familia", "13:10", "17"))
        listaChats.add(ChatMessage("Charlie", "Yo voy a descansar en casa", "13:15", "18"))
        listaChats.add(ChatMessage("Bob", "¿Quieres unirte a nosotros en la excursión?", "13:20", "19"))
        listaChats.add(ChatMessage("Alice", "Gracias por la invitación, pero ya tengo planes", "13:25", "20"))

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_chats)
        inicializarRecyclerView(listaChats, recyclerView)

    }

    private fun inicializarRecyclerView(listaChats: ArrayList<ChatMessage>, recyclerView: RecyclerView) {
        val adaptador = ChatAdapter(listaChats, this, recyclerView)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
        adaptador.onItemClick = {
            val intent1 = Intent(this, ChatActivity::class.java)
            startActivity(intent1)
        }

    }

}
