package com.fatec.at2_media_geral.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fatec.at2_media_geral.ui.state.AlunoState

@Composable
fun CadastroScreen(
    alunoState: AlunoState,
    onNavigateToNotas: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cadastro do Aluno",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = alunoState.nomeAluno,
            onValueChange = { alunoState.nomeAluno = it },
            label = { Text("Nome do aluno") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (alunoState.nomeAluno.isNotBlank()) {
                    alunoState.cadastrarAluno(alunoState.nomeAluno)
                    onNavigateToNotas(alunoState.nomeAluno)
                }
            },
            enabled = alunoState.nomeAluno.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar para Notas")
        }
    }
}