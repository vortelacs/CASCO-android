package com.asig.casco.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asig.casco.api.viewmodel.NewsViewModel
import com.asig.casco.api.viewmodel.PartnerViewModel
import com.asig.casco.screens.common.ImageCard


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsPagerCarousel(

) {
    val newsViewModel : NewsViewModel = hiltViewModel()
    var newsList = newsViewModel.newsResult.collectAsState(initial = ArrayList())
    val pagerState = rememberPagerState(newsList.value.size)

    LaunchedEffect(key1 = "loadNews") {
        newsViewModel.getAllNews()
    }

    HorizontalPager(pageCount = newsList.value.size, state = pagerState) { page ->
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.primary,
            border = null,
            elevation = 1.dp
        ) {
            ImageCard(contentDescription = "news", title = newsList.value[page].title, imageUrl = newsList.value[page].img)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PartnerPagerCarousel(

) {
    val partnerViewModel : PartnerViewModel = hiltViewModel()
    var partnerList = partnerViewModel.partnerResult.collectAsState(initial = ArrayList())
    val pagerState = rememberPagerState(partnerList.value.size)

    LaunchedEffect(key1 = "loadNews") {
        partnerViewModel.getAllPartner()
    }

    HorizontalPager(pageCount = partnerList.value.size, state = pagerState) { page ->
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.primary,
            border = null,
            elevation = 1.dp
        ) {
            ImageCard(contentDescription = "news", title = partnerList.value[page].name, imageUrl = partnerList.value[page].img)
        }
    }
}