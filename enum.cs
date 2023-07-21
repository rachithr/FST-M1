public class MyRequestDto
{
    // Other properties

    [Required(ErrorMessage = "The Status field is required")]
    public Status RequestStatus { get; set; }
}


public void ConfigureServices(IServiceCollection services)
{
    // Other configurations

    services.AddControllers()
            .AddJsonOptions(options =>
            {
                options.JsonSerializerOptions.PropertyNamingPolicy = null;
                options.JsonSerializerOptions.DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull;
            })
            .ConfigureApiBehaviorOptions(options =>
            {
                options.InvalidModelStateResponseFactory = context =>
                {
                    var errors = context.ModelState.Values
                        .SelectMany(modelState => modelState.Errors)
                        .Select(error => error.ErrorMessage)
                        .ToList();

                    return new BadRequestObjectResult(new
                    {
                        Status = 400,
                        Errors = errors
                    });
                };
            });

    // Other configurations
}
