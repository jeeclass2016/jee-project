/**
 * 
 */

function setTimeOutMenu() {
	setTimeout(function() {
		// Slide

		$('#menu1 > li > a').click(
				function() {
					$(this).toggleClass('expanded').toggleClass('collapsed')
							.parent().find('> ul').slideToggle('medium');
				});
		$('#example1 .expand_all').click(
				function() {
					$('#menu1 > li > a.collapsed').addClass('expanded')
							.removeClass('collapsed').parent().find('> ul')
							.slideDown('medium');
				});
		$('#example1 .collapse_all').click(
				function() {
					$('#menu1 > li > a.expanded').addClass('collapsed')
							.removeClass('expanded').parent().find('> ul')
							.slideUp('medium');
				});
		// Fade
		$('#menu2 > li > a.expanded + ul').fadeIn();
		$('#menu2 > li > a').click(function() {
			var el = $(this).parent().find('> ul');
			if ($(this).hasClass('collapsed'))
				$(el).fadeIn();
			else
				$(el).fadeOut();
			$(this).toggleClass('expanded').toggleClass('collapsed');
		});
		$('#example2 .expand_all').click(
				function() {
					$('#menu2 > li > a.collapsed').addClass('expanded')
							.removeClass('collapsed').parent().find('> ul')
							.fadeIn();
				});
		$('#example2 .collapse_all').click(
				function() {
					$('#menu2 > li > a.expanded').addClass('collapsed')
							.removeClass('expanded').parent().find('> ul')
							.fadeOut();
				});
		// Grow/Shrink
		$('#menu3 > li > a.expanded + ul').show('normal');
		$('#menu3 > li > a').click(
				function() {
					$(this).toggleClass('expanded').toggleClass('collapsed')
							.parent().find('> ul').toggle('normal');
				});
		$('#example3 .expand_all').click(
				function() {
					$('#menu3 > li > a.collapsed').addClass('expanded')
							.removeClass('collapsed').parent().find('> ul')
							.show('normal');
				});
		$('#example3 .collapse_all').click(
				function() {
					$('#menu3 > li > a.expanded').addClass('collapsed')
							.removeClass('expanded').parent().find('> ul')
							.hide('normal');
				});
		// Appear/Disappear
		$('#menu4 > li > a.expanded + ul').show();
		$('#menu4 > li > a').click(
				function() {
					$(this).toggleClass('expanded').toggleClass('collapsed')
							.parent().find('> ul').toggle();
				});
		$('#example4 .expand_all').click(
				function() {
					$('#menu4 > li > a.collapsed').addClass('expanded')
							.removeClass('collapsed').parent().find('> ul')
							.show();
				});
		$('#example4 .collapse_all').click(
				function() {
					$('#menu4 > li > a.expanded').addClass('collapsed')
							.removeClass('expanded').parent().find('> ul')
							.hide();
				});
		// Accordion
		$('#menu5 > li > a.expanded + ul').slideToggle('medium');
		$('#menu5 > li > a').click(
				function() {
					$('#menu5 > li > a.expanded').not(this).toggleClass(
							'expanded').toggleClass('collapsed').parent().find(
							'> ul').slideToggle('medium');
					$(this).toggleClass('expanded').toggleClass('collapsed')
							.parent().find('> ul').slideToggle('medium');
				});
	}, 250);
}