using System;
using System.ComponentModel;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.ModelBinding;

public class EnumModelBinder : IModelBinder
{
    public Task BindModelAsync(ModelBindingContext bindingContext)
    {
        if (!typeof(Enum).IsAssignableFrom(bindingContext.ModelType))
        {
            bindingContext.Result = ModelBindingResult.Failed();
            return Task.CompletedTask;
        }

        var valueProviderResult = bindingContext.ValueProvider.GetValue(bindingContext.ModelName);
        if (valueProviderResult == ValueProviderResult.None)
        {
            bindingContext.Result = ModelBindingResult.Failed();
            return Task.CompletedTask;
        }

        var value = valueProviderResult.FirstValue;
        if (Enum.TryParse(bindingContext.ModelType, value, ignoreCase: true, out var enumValue))
        {
            if (Enum.IsDefined(bindingContext.ModelType, enumValue))
            {
                bindingContext.Result = ModelBindingResult.Success(enumValue);
                return Task.CompletedTask;
            }
        }

        bindingContext.ModelState.AddModelError(bindingContext.ModelName, "Invalid value for enum");
        bindingContext.Result = ModelBindingResult.Failed();
        return Task.CompletedTask;
    }
}
public void ConfigureServices(IServiceCollection services)
{
    // Other configurations

    services.AddControllers(options =>
    {
        options.ModelBinderProviders.Insert(0, new BinderTypeModelBinderProvider(typeof(Enum), new EnumModelBinder()));
    });
}


using Microsoft.AspNetCore.Mvc.ModelBinding;

public class EnumModelBinderProvider : IModelBinderProvider
{
    public IModelBinder? GetBinder(ModelBinderProviderContext context)
    {
        if (context.Metadata.ModelType.IsEnum)
        {
            return new EnumModelBinder();
        }

        return null;
    }
}

