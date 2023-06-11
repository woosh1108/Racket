function scroll_follow( id )
{
  $(window).scroll(function() {
    var position = $(window).scrollTop();
    var windowHeight = $(window).height();
    var footerHeight = $('footer').height();
    var scrollableHeight = $('body').height() - windowHeight;
    var maxTopPosition = scrollableHeight - footerHeight;

    if (position <= maxTopPosition) {
      $(id).stop().animate({ top: position + "px" }, 300);
    } else {
      $(id).stop().animate({ top: maxTopPosition + "px" }, 300);
    }
  });
}

scroll_follow("#scroll");
