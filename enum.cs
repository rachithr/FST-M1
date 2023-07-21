using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.ModelBinding;

public class EnumMemberNameModelBinder<TEnum> : IModelBinder where TEnum : struct, Enum
{
    public Task BindModelAsync(ModelBindingContext bindingContext)
    {
        var modelName = bindingContext.ModelName;
        var valueProviderResult = bindingContext.ValueProvider.GetValue(modelName);

        if (valueProviderResult == ValueProviderResult.None)
        {
            bindingContext.ModelState.AddModelError(modelName, "Value for enum is missing");
            bindingContext.Result = ModelBindingResult.Failed();
            return Task.CompletedTask;
        }

        var value = valueProviderResult.FirstValue;
        if (Enum.TryParse<TEnum>(value, ignoreCase: true, out var enumValue))
        {
            if (Enum.IsDefined(typeof(TEnum), enumValue))
            {
                bindingContext.Result = ModelBindingResult.Success(enumValue);
                return Task.CompletedTask;
            }
        }

        bindingContext.ModelState.AddModelError(modelName, "Invalid value for enum");
        bindingContext.Result = ModelBindingResult.Failed();
        return Task.CompletedTask;
    }
}
public void ConfigureServices(IServiceCollection services)
{
    // Other configurations

    services.AddControllers(options =>
    {
        options.ModelBinderProviders.Insert(0, new BinderTypeModelBinderProvider(typeof(Status), new EnumMemberNameModelBinder<Status>()));
    });
}
