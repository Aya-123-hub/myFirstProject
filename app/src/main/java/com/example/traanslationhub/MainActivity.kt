package com.example.traanslationhub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp



class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var spinnerSourceLanguage: Spinner
    private lateinit var spinnerTargetLanguage: Spinner
    private lateinit var buttonTranslate: Button
    private lateinit var textViewTranslated: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity)

        editTextInput = findViewById(R.id.editTextInput)
        spinnerSourceLanguage = findViewById(R.id.spinnerSourceLanguage)
        spinnerTargetLanguage = findViewById(R.id.spinnerTargetLanguage)
        buttonTranslate = findViewById(R.id.buttonTranslate)
        textViewTranslated = findViewById(R.id.textViewTranslated)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        buttonTranslate.setOnClickListener {
            val inputText = editTextInput.text.toString()
            val sourceLanguage = spinnerSourceLanguage.selectedItem.toString()
            val targetLanguage = spinnerTargetLanguage.selectedItem.toString()

            // Download language models
            downloadLanguageModels(sourceLanguage, targetLanguage) {
                // Perform translation
                translateText(inputText, sourceLanguage, targetLanguage,)
            }
        }
    }


    private fun downloadLanguageModels(
        sourceLanguage: String,
        targetLanguage: String,
        onDownloadComplete: () -> Unit
    ) {
        val sourceLanguageCode = sourceLanguage.getFirebaseTranslateLanguageCode()
        val targetLanguageCode = targetLanguage.getFirebaseTranslateLanguageCode()

        val sourceModel = FirebaseTranslateRemoteModel.Builder(sourceLanguageCode).build()
        val targetModel = FirebaseTranslateRemoteModel.Builder(targetLanguageCode).build()

        val modelManager = FirebaseTranslateModelManager.getInstance()

        modelManager.downloadRemoteModelIfUpdated(sourceModel)
            .addOnSuccessListener {
                modelManager.downloadRemoteModelIfUpdated(targetModel)
                    .addOnSuccessListener {
                        // Language models downloaded successfully
                        onDownloadComplete()
                    }
                    .addOnFailureListener { exception: Exception ->
                        // Handle target language model download failure
                        showErrorMessage("Failed to download target language model: $exception")
                    }
            }
            .addOnFailureListener { exception: Exception ->
                // Handle source language model download failure
                showErrorMessage("Failed to download source language model: $exception")
            }
    }

    private fun showErrorMessage(message: String) {
        // Implement a function to display an error message to the user
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun translateText(
        inputText: String,
        sourceLanguage: String,
        targetLanguage: String,
        FirebaseNaturalLanguage: Any
    ) {
        val options = FirebaseTranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguage.getFirebaseTranslateLanguageCode())
            .setTargetLanguage(targetLanguage.getFirebaseTranslateLanguageCode())
            .build()

        translator.downloadModelIfNeeded()
            .addOnSuccessListener(OnSuccessListener<TranslateTextResult> {
                // Translation model downloaded successfully
                translator.translate(inputText)
                    .addOnSuccessListener { translationResult ->
                        val translatedText = translationResult.translatedText
                        textViewTranslated.text = translatedText
                    }
                    .addOnFailureListener { exception: Exception ->
                        // Handle translation failure
                        showErrorMessage("Failed to translate text: $exception")
                    }
            })
            .addOnFailureListener { exception: Exception ->
                // Handle model download failure
                showErrorMessage("Failed to download translation model: $exception")
            }
    }

    private fun String.getFirebaseTranslateLanguageCode(): Int {
        println("Retrieving language code for: $this")
        return when (this) {
            "English" -> {
                println("Returning FirebaseTranslateLanguage.EN")
                FirebaseTranslateLanguage.EN
            }

            "Indonesian" -> {
                println("Returning FirebaseTranslateLanguage.ID")
                FirebaseTranslateLanguage.ID
            }

            "Vietnamese" -> {
                println("Returning FirebaseTranslateLanguage.VI")
                FirebaseTranslateLanguage.VI
            }

            "Spanish" -> {
                println("Returning FirebaseTranslateLanguage.ES")
                FirebaseTranslateLanguage.ES
            }

            "French" -> {
                println("Returning FirebaseTranslateLanguage.FR")
                FirebaseTranslateLanguage.FR
            }

            "German" -> {
                println("Returning FirebaseTranslateLanguage.DE")
                FirebaseTranslateLanguage.DE
            }

            "Arabic" -> {
                println("Returning FirebaseTranslateLanguage.AR")
                FirebaseTranslateLanguage.AR
            }

            "Hindi" -> {
                println("Returning FirebaseTranslateLanguage.HI")
                FirebaseTranslateLanguage.HI
            }

            "Chinese" -> {
                println("Returning FirebaseTranslateLanguage.ZH")
                FirebaseTranslateLanguage.ZH
            }

            "Bengali" -> {
                println("Returning FirebaseTranslateLanguage.BN")
                FirebaseTranslateLanguage.BN
            }

            "Urdu" -> {
                println("Returning FirebaseTranslateLanguage.UR")
                FirebaseTranslateLanguage.UR
            }

            "Afrikaans" -> {
                println("Returning FirebaseTranslateLanguage.AF")
                FirebaseTranslateLanguage.AF
            }

            "Swahili" -> {
                println("Returning FirebaseTranslateLanguage.SW")
                FirebaseTranslateLanguage.SW
            }

            "Japanese" -> {
                println("Returning FirebaseTranslateLanguage.JA")
                FirebaseTranslateLanguage.JA
            }

            "Korean" -> {
                println("Returning FirebaseTranslateLanguage.KO")
                FirebaseTranslateLanguage.KO
            }

            "Thai" -> {
                println("Returning FirebaseTranslateLanguage.TH")
                FirebaseTranslateLanguage.TH
            }

            "Russian" -> {
                println("Returning FirebaseTranslateLanguage.RU")
                FirebaseTranslateLanguage.RU
            }

            "Turkish" -> {
                println("Returning FirebaseTranslateLanguage.TR")
                FirebaseTranslateLanguage.TR
            }

            "Portuguese" -> {
                println("Returning FirebaseTranslateLanguage.PT")
                FirebaseTranslateLanguage.PT
            }

            "Italian" -> {
                println("Returning FirebaseTranslateLanguage.IT")
                FirebaseTranslateLanguage.IT
            }

            "Dutch" -> {
                println("Returning FirebaseTranslateLanguage.NL")
                FirebaseTranslateLanguage.NL
            }

            "Polish" -> {
                println("Returning FirebaseTranslateLanguage.PL")
                FirebaseTranslateLanguage.PL
            }

            "Swedish" -> {
                println("Returning FirebaseTranslateLanguage.SV")
                FirebaseTranslateLanguage.SV
            }

            "Greek" -> {
                println("Returning FirebaseTranslateLanguage.EL")
                FirebaseTranslateLanguage.EL
            }

            "Romanian" -> {
                println("Returning FirebaseTranslateLanguage.RO")
                FirebaseTranslateLanguage.RO
            }

            else -> {
                println("Language not found, returning FirebaseTranslateLanguage.EN")
                FirebaseTranslateLanguage.EN
            }
        }
    }

    private fun Any.addOnFailureListener(function: (Exception) -> Unit) {
        // Implement the addOnFailureListener function
        // This function should handle the failure cases
        try {
            function(Exception("Failure occurred"))
        } catch (e: Exception) {
            // Handle the exception and call the provided function
            function(e)
        }
    }
}

private fun Any.getInstance(): Any {
    TODO("Not yet implemented")
}

private fun Any.downloadRemoteModelIfUpdated(sourceModel: FirebaseTranslateRemoteModel): Any {
    val modelManager = FirebaseTranslateModelManager.getInstance()

    return modelManager.downloadRemoteModelIfNeeded(this as FirebaseTranslateRemoteModel)
        .addOnSuccessListener {
            println("Remote model downloaded successfully")
        }
        .addOnFailureListener { exception: Exception ->
            println("Failed to download remote model: $exception")
        }
}

private fun Any.addOnFailureListener(function: (Exception) -> Unit): Any {
    try {
        function(Exception("Failure occurred"))
    } catch (e: Exception) {
        function(e)
    }
    return this
}

private fun Any.downloadRemoteModelIfNeeded(firebaseTranslateRemoteModel: FirebaseTranslateRemoteModel): Any {
    val modelManager = FirebaseTranslateModelManager.getInstance()

    return modelManager.downloadRemoteModelIfNeeded(firebaseTranslateRemoteModel)
        .addOnSuccessListener {
            println("Remote model downloaded successfully")
        }
        .addOnFailureListener { exception ->
            println("Failed to download remote model: $exception")
        }
}

private fun Any.addOnSuccessListener(function: () -> Unit): Any {
    // Implement the addOnSuccessListener function
    // This function should handle the success cases
    function()
    return this
}





