<aside id="sidebar" class="p-3 shadow-sm">


  <div class="d-flex flex-row">
    <div class="ignekcolorbox"></div>
    <div><a href="#" class="custom-header">${site_name}</a></div>
  </div>

  <!-- <button class="btn btn-outline-primary" id="sidebarToggle">
  <i class="bi bi-list"></i>
</button> -->

  <div class="text-center mb-4">
    <img src="${user.getPortraitURL(themeDisplay)}" class="rounded-circle myprofileimg" alt="User" width="80">
    <div class="mt-2 fw-semibold">
      <#if is_signed_in>
        ${user_name}
      <#else>
        Guest
      </#if>
    </div>
    <div class="role">HR</div>
  </div>

  <ul class="nav flex-column nav2">
  <li class="nav-item mb-2">
    <a href="#" class="nav-link <#if themeDisplay.getURLCurrent()?contains("/newtestpage")>active</#if>">
      <i class="bi bi-house-door mr-2"></i> Dashboard
    </a>
  </li>
  <li class="nav-item mb-2">
    <a href="#" class="nav-link <#if themeDisplay.getURLCurrent()?contains("/Employee")>active</#if>">
      <i class="fa-solid fa-graduation-cap mr-2"></i> Employee
    </a>
  </li>

    <li class="nav-item mb-2">
      <a href="#" class="nav-link d-flex align-items-center mr-3">
        <i class="fa-solid fa-graduation-cap mr-2"></i> HR
      </a>
    </li>
    <li class="nav-item mb-2">
      <a href="#" class="nav-link d-flex align-items-center">
        <i class="bi bi-gear mr-2"></i></i> Settings
      </a>
    </li>

    <div class="sign-out-div">
  <#if is_signed_in>
  <a href="${sign_out_url}" class="sign-out">Logout</a>
  <i class="fa-solid fa-right-from-bracket ml-2"></i>
  <#else>
  <a href="#" class="sign-out">Sign In</a>
  <!-- <i class="fa-solid fa-right-from-bracket"></i> -->
  </#if>
  </div>

  </ul>

  
</aside>
