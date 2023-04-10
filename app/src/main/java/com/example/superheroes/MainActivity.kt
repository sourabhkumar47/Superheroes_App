package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Data.HeroesRepository.heroes
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Superhero()
                }
            }
        }
    }
}

@Composable
fun Superhero() {
    LazyColumn() {
        items(heroes) {
            SuperheroItem(hero = it)
        }
    }
}

@Composable
fun SuperheroItem(hero: Hero, modifier: Modifier = Modifier) {

    Card() {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            HeroInformation(hero.nameRes, hero.descriptionRes)
            HeroImage(hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(@StringRes heroName: Int, heroDescription: Int) {
    Column() {
        Text(text = stringResource(heroName))
        Text(text = stringResource(heroDescription))
    }
}

@Composable
fun HeroImage(heroImage: Int) {
    Image(painter = painterResource(heroImage), contentDescription = null)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        Superhero()
    }
}