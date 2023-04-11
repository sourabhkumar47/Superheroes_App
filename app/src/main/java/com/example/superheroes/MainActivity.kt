package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
    LazyColumn(
        modifier = Modifier
            .padding(
                end = 16.dp,
                start = 16.dp
            )
    ) {
        items(heroes) {
            SuperheroItem(hero = it)
        }
    }
}

@Composable
fun SuperheroItem(hero: Hero, modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                end = 16.dp,
                start = 16.dp
            )
            .clip(RoundedCornerShape(16.dp))
            .height(84.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            HeroInformation(hero.nameRes, hero.descriptionRes)
            Spacer(Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            HeroImage(hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(@StringRes heroName: Int, heroDescription: Int) {
    Column() {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun HeroImage(heroImage: Int) {
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .size(84.dp),
        contentScale =ContentScale.Crop,
        painter = painterResource(heroImage),
        contentDescription = null,


        )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        Superhero()
    }
}