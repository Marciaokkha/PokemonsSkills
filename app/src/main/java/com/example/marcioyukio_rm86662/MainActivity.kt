package com.example.marcioyukio_rm86662

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.marcioyukio_rm86662.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding
    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)


        //This change the theme of the application
        databind.themeChangerButton.setOnClickListener {
            Theme.switchTheme()
            recreate()
        }

        //Lets create a "Pokemon" object, this object will be used in two moments:
        //1. To be sented to SkillsActivity with the current viewed skills
        //2. After the return of the skill from SkillsActivity,
        //those skills will be saved in this object
        pokemon = Pokemon(getString(R.string.name),
            getString(R.string.descriptionText),
            mutableListOf())

        //Here's the listener to call the SkillsActivity
        databind.skillButton.setOnClickListener{
            val intent = Intent(this, SkillsActivity::class.java)
            intent.putExtra("pokemonAttributes", pokemon)
            register.launch(intent)
        }
    }

    //How the method below is working:
    //0. Checks if the return from the SkillsActivity is valid
    //1. If yes, clear the Pokemon Skills from the pokemon object* (MainActivity only)
    //2. Verifies each skill:
        //From the SkillActivity in case of a not selected skill the return is ""
        //this way the verifier will ignore those ones.
    //3. Add each valid skill to the skillsConcatenated variable, and the pokemon object*.
    //4. Checks if have skills to be listed:
        //If yes: list them on the skillText
        //If no: add the default text
    //* = Are the same objects
    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val pokemonExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra("pokemonSkills", Pokemon::class.java)
                } else {
                    data.getParcelableExtra<Pokemon>("pokemonSkills")
                }
                pokemonExtra?.let {
                    var skillsConcatenated = ""
                    pokemon.skills.clear()
                    pokemonExtra.skills.forEach {
                        if (it != ""){
                            skillsConcatenated +="> ${it}\n"
                            pokemon.skills.add(it)
                        }
                    }
                    if(skillsConcatenated != "") {
                        databind.skillText.text = skillsConcatenated
                    } else {
                        databind.skillText.text = getString(R.string.skillText)
                    }
                }
            }
        }
    }
}