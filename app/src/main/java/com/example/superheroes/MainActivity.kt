package com.example.superheroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Superhero() {
    Scaffold(
        topBar = {
            SuperheroAppBar()
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(
                    end = 16.dp,
                    start = 16.dp
                )
        ) {
            items(heroes) {
                SuperheroItem(
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    hero = it
                )
            }
        }
    }
}

@Composable
fun SuperheroItem(hero: Hero, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .padding(
                8.dp
//                top = 8.dp,
//                bottom = 8.dp,
//                end = 16.dp,
//                start = 16.dp
            ),
//            .clip(RoundedCornerShape(16.dp))
//            .height(84.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .sizeIn(minHeight = 72.dp)
        ) {
            HeroInformation(hero.nameRes, hero.descriptionRes)
            Spacer(Modifier.weight(1f))
//            Spacer(Modifier.width(16.dp))
            HeroImage(hero.imageRes)
        }
    }
}


@Composable
fun SuperheroAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun HeroInformation(
    @StringRes heroName: Int,
    heroDescription: Int
) {
    Column(
        modifier = Modifier.width(225.dp),
        verticalArrangement = Arrangement.Center
    ) {
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
    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Image(
            painter = painterResource(heroImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        Superhero()
    }
}