# 🎵 Sistema de Gerenciamento de Músicas e Playlists

## 📖 Descrição do Projeto

Este projeto foi desenvolvido como parte das disciplinas de *Linguagem de Programação Visual, **Banco de Dados* e *Engenharia de Software*. Ele permite que o usuário:

- Gerencie músicas
- Reproduza playlists
- Reproduza áudios locais ou via link externo
- Visualize estatísticas
---

## 🛠 Tecnologias Utilizadas

- Java JDK (versões 17 a 24)  
- Java Swing / JavaFX (com SceneBuilder)  
- MySQL (via XAMPP)  
- JDBC (com MySQL Connector/J)  
- NetBeans IDE 22 ou IntelliJ IDEA  
- SceneBuilder (GluonHQ) para edição de interfaces .fxml  

---

## 👥 Membros do Grupo

- Ana Clara de Jesus Régis  
- João Victor Oliveira Mendonça  
- Kalebe Nobre de Aquino  

---

## 🚀 Como Executar a Versão Swing (NetBeans)

### ✅ Pré-requisitos

- NetBeans IDE  
- XAMPP  
- MySQL Connector/J (.jar)  

### 🛠 Passos

#### Banco de Dados

1. Inicie o Apache e o MySQL no XAMPP  
2. Acesse o *phpMyAdmin* e crie o banco playlistdb  
3. Execute os arquivos SQL:

database/schema.sql
database/dados_iniciais.sql


#### Projeto no NetBeans

1. Abra o projeto via File > Open Project...  
2. Adicione o *MySQL Connector/J* em Libraries > Add JAR/Folder  
3. Verifique a classe Conexao.java (usuário padrão: root, senha: vazia)  

#### Executar

1. Clique com o botão direito > Clean and Build  
2. Depois > Run

---

## 💻 Como Executar a Versão JavaFX (via JAR)

Você pode baixar o .jar da versão final pelo botão abaixo:

🔗 [Download dos arquivos .jar (Google Drive)](https://drive.google.com/drive/folders/1S9GJKsK7z-pCVtsKoFPpwEqFj8-UQBSS?hl=pt-br)

### ✅ Requisitos

- Java JDK 17, 21 ou 24  
- JavaFX SDK 21 ou 24 — [Baixar SDK JavaFX](https://gluonhq.com/products/javafx/)  
- (Opcional) SceneBuilder (para editar os .fxml)  

### 📦 Comando para Executar o .jar (via Terminal)

java --module-path "C:\caminho\para\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.media --enable-native-access=ALL-UNNAMED -jar "C:\Users\seu_usuario\Downloads\arquivo.jar"

📌 Importante: Altere os caminhos de acordo com a sua instalação.

---

## 🧰 Instruções Detalhadas para Rodar o Código no IntelliJ IDEA

### 1. Abrir o Projeto
Vá em *File > Open...* e selecione a pasta raiz do projeto.

### 2. Instalar o JavaFX SDK
- Baixe em: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- Extraia o arquivo e copie o caminho da pasta /lib

### 3. Configurar VM options
- Vá em *Run > Edit Configurations*
- No campo *VM options*, adicione:

--module-path "C:\caminho\para\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.media --enable-native-access=ALL-UNNAMED

✅ Altere o caminho para o local correto do seu SDK.

### 4. Remover o Sistema Modular (se necessário)

Se o projeto apresentar erro relacionado ao module-info.java:

- Delete ou comente o arquivo module-info.java
- Verifique se os imports estão corretos

🔧 O projeto continuará funcionando normalmente com Maven e JavaFX mesmo sem o módulo.

---

### 5. Configurar o SceneBuilder

- Instale o SceneBuilder: [https://gluonhq.com/products/scene-builder/](https://gluonhq.com/products/scene-builder/)

No IntelliJ, vá em:  
*File > Settings > Languages & Frameworks > JavaFX > Path to SceneBuilder*

Para abrir arquivos .fxml:  
➡ Clique com o botão direito > *Open in SceneBuilder*

---

## 📷 Telas do Sistema (JavaFX)

### 🏠 Tela Inicial
<img width="1075" height="710" alt="telainicial" src="https://github.com/user-attachments/assets/a1452eef-d6a7-4d87-808c-5a7a3d8732a9" />

- Navegação lateral  
- Filtro de músicas por nome/artista  
- Player de áudio com imagem associada  

### 🎶 Biblioteca e Playlists
<img width="1082" height="748" alt="Playlisttela" src="https://github.com/user-attachments/assets/13223775-580b-4646-8d79-f8b110396594" />

- Lista de músicas  
- Player integrado  

### 📊 Estatísticas
<img width="1068" height="825" alt="estatisticastela" src="https://github.com/user-attachments/assets/4592208a-277b-4ff7-931d-d60ce6c2b6f1" />

- Gráfico de músicas mais ouvidas  

---

Muito obrigado!
