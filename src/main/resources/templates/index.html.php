<?php Theme::header('index')?>
<main>
       <div class="popular_blocks">
            <h1>Популярные сферы обучения</h1>
            <div class="popular_courses">
                <?php foreach($sliders as $slider):?>
                <a href="<?= $slider['url'] ?>"><div>
                        <img src="<?= $slider['photo'] ?>">
                        <p><?= $slider['text'] ?></p>
                </div></a>
                <?php endforeach; ?> 
            </div>
        </div>

        <div class="innumbers">
            <h1>Мы в цифрах</h1>
            <div class="innumbers_blocks"> 
                <?php foreach ($numbers as $number):?> 
                <div>
                    <img src='<?= $number['photo'] ?>'>
                    <p class="number"><?= $number['number'] ?></p>
                    <p class="number_name"><?= $number['name'] ?></p>
                </div>
                <?php endforeach;?>
            </div>
        </div>

        <div class="whyimportant">
            <div>
               <h1>Почему важно повышать квалификацию?</h1>
               <ul>
				   <li>Потребность в квалифицированных специалистах</li>
				   <li>Карьерный рост в своей профессиональной сфере</li>
				   <li>Развитие и совершенствование навыков</li>
			   </ul>
            </div>
            <img src="<?= $themePath ?>/img/career1.png">
        </div> 
        
        <div class="nextcourses">
            <h1>Идет набор на курсы</h1>
            <div class="calendar">
                <?php foreach($near_courses as $item) {
                    Theme::block('courses/nearest_courses', [ 
			'baseUrl' => $baseUrl,
                        'item' => $item
                    ]);
                } ?>
            </div>
            <div class="calendar_btn_div">
                <a href="/calendar" class="calendar_btn">Календарь обучения</a>
            </div>
        </div>
            
            <a id="success"></a>
            <?php Theme::block('feedback/feedback_item') ?>
         
    </main>
<?php Theme::footer('index') ?>