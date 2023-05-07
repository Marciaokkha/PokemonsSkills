package com.example.marcioyukio_rm86662

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marcioyukio_rm86662.databinding.ActivityMainBinding
import com.example.marcioyukio_rm86662.databinding.ActivitySkillsBinding

class SkillsActivity : AppCompatActivity() {

    private lateinit var databind: ActivitySkillsBinding
    private var skill1: String = ""
    private var skill2: String = ""
    private var skill3: String = ""
    private var skill4: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        databind = ActivitySkillsBinding.inflate(layoutInflater)
        setContentView(databind.root)

        //Gets our pokemon data from the MainActivity
        val pokemonData = intent.getParcelableExtra<Pokemon>("pokemonAttributes")

        //This ensures that all skills listed will be marked
        if (pokemonData != null) {
            databind.switchSkill1.isChecked = pokemonData.skills.contains(getString(R.string.skill1Text))
            databind.switchSkill2.isChecked = pokemonData.skills.contains(getString(R.string.skill2Text))
            databind.switchSkill3.isChecked = pokemonData.skills.contains(getString(R.string.skill3Text))
            databind.switchSkill4.isChecked = pokemonData.skills.contains(getString(R.string.skill4Text))
        }

        //Here have a few forms to work, but I choose to:
        //0. The listener detects that the user wants to save the skills
        //1. Check if the switch is selected, if yes save the Skill in a global variable
        //2. Clear the skills from the pokemonData (Origin: MainActivity)
        //3. Add the skills to pokemonData
        //4. Return the pokemonData to Main
        databind.skillUpdateButton.setOnClickListener {
            Intent().apply {
                if(databind.switchSkill1.isChecked){
                    skill1 = getString(R.string.skill1Text)
                }
                if(databind.switchSkill2.isChecked){
                    skill2 = getString(R.string.skill2Text)
                }
                if(databind.switchSkill3.isChecked){
                    skill3 = getString(R.string.skill3Text)
                }
                if(databind.switchSkill4.isChecked){
                    skill4 = getString(R.string.skill4Text)
                }
                if (pokemonData != null) {
                    pokemonData.skills.clear()
                    pokemonData.skills.add(skill1)
                    pokemonData.skills.add(skill2)
                    pokemonData.skills.add(skill3)
                    pokemonData.skills.add(skill4)
                }
                putExtra("pokemonSkills", pokemonData)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }
}