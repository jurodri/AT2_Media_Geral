package com.fatec.at2_media_geral.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fatec.at2_media_geral.model.Aluno
import com.fatec.at2_media_geral.ui.state.AlunoState

@Composable
fun ResultadoScreen(
    alunoState: AlunoState,
    nomeAluno: String,
    nota1: Double,
    nota2: Double,
    nota3: Double,
    onNavigateToCadastro: () -> Unit
) {
    val alunoTemp = Aluno(nomeAluno).apply {
        adicionarNota(nota1)
        adicionarNota(nota2)
        adicionarNota(nota3)
    }

    val media = alunoTemp.calcularMedia()
    val status = alunoTemp.obterStatus()

    LaunchedEffect(Unit) {
        alunoState.aluno = alunoTemp
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultado Final",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Aluno: $nomeAluno",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("TP1: ${String.format("%.1f", nota1)}")
                Text("TP2: ${String.format("%.1f", nota2)}")
                Text("TP3: ${String.format("%.1f", nota3)}")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Média: ${String.format("%.1f", media)}",
                    style = MaterialTheme.typography.titleMedium
                )

                val statusColor = when (status) {
                    "Reprovado" -> Color.Red
                    "Aprovado" -> Color.Green
                    "Ótimo Aproveitamento" -> Color.Blue
                    else -> Color.Black
                }

                Text(
                    text = "Status: $status",
                    color = statusColor,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                alunoState.limparDados()
                onNavigateToCadastro()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Novo Cálculo")
        }
    }
}